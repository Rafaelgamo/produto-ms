package api.catalogo.produtos.infra.gateways;

import api.catalogo.produtos.infra.persistence.ProdutoEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
public class ImportacaoJobConfiguration {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job job(Step passoInicial, JobRepository jobRepository){
        return new JobBuilder("gerar-produtos", jobRepository)
                .start(passoInicial)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step passoInicial(ItemReader<ProdutoEntity> reader, ItemWriter<ProdutoEntity> writer, JobRepository jobRepository){
        return new StepBuilder("passo-inicial", jobRepository)
                .<ProdutoEntity, ProdutoEntity>chunk(100,transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<ProdutoEntity> reader(){
        return new FlatFileItemReaderBuilder<ProdutoEntity>()
                .name("leitura-csv")
                .resource(new FileSystemResource("files/catalogo.csv"))
                .comments("--")
                .delimited()
                .delimiter(";")
                .names("nome","tipo","descricao","valor", "quantidadeEstoque","quantidadeReservada", "horaImportacao")
                .fieldSetMapper(new ImportacaoMapper())
                .build();
    }

    @Bean
    public ItemWriter<ProdutoEntity> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<ProdutoEntity>()
                .dataSource(dataSource)
                .sql("INSERT INTO produto (nome, tipo, descricao, valor, quantidade_estoque, quantidade_reservada, hora_importacao) VALUES" +
                   "(:nome, :tipo, :descricao, :valor, :quantidadeEstoque, :quantidadeReservada , :horaImportacao)"
                )
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }


}
