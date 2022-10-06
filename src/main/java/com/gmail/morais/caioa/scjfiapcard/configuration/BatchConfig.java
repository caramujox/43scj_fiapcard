package com.gmail.morais.caioa.scjfiapcard.configuration;

import com.gmail.morais.caioa.scjfiapcard.entity.Aluno;
import com.gmail.morais.caioa.scjfiapcard.util.GeradorCPF;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Aluno> fileReader(@Value("${input.file.target}") Resource resource) {
        return new FlatFileItemReaderBuilder<Aluno>()
                .name("read file")
                .resource(resource)
                .targetType(Aluno.class)
                .delimited().delimiter(";").names("nome", "rm")
                .build();
    }

    @Bean
    public ItemProcessor<Aluno, Aluno> processor() {
        GeradorCPF cpf = new GeradorCPF();
        return aluno -> {
            aluno.setNome(aluno.getNome().toUpperCase());
            aluno.setCpf(cpf.cpf(false));
            aluno.setRm(aluno.getRm().replaceAll("\\.+", "").replace("-", "").trim());
            return aluno;
        };
    }

    @Bean
    public JdbcBatchItemWriter databaseWriter(DataSource datasource) {
        return new JdbcBatchItemWriterBuilder<Aluno>()
                .dataSource(datasource)
                .sql("insert into TB_ALUNO (NOME, RM, cpf) VALUES (:nome, :rm, :cpf)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory,
                     ItemReader<Aluno> itemReader,
                     ItemWriter<Aluno> itemWriter,
                     ItemProcessor<Aluno, Aluno> processor) {
        return stepBuilderFactory.get("Step file to DB")
                .<Aluno, Aluno>chunk(2)
                .reader(itemReader)
                .processor(processor)
                .writer(itemWriter)
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   Step step) {
        return jobBuilderFactory.get("alunoJob")
                .start(step)
                .build();
    }




}
