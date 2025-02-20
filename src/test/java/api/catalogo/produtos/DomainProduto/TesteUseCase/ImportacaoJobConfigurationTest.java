package api.catalogo.produtos.DomainProduto.TesteUseCase;

import api.catalogo.produtos.infra.gateways.ImportacaoJobConfiguration;
import api.catalogo.produtos.infra.gateways.ImportacaoMapper;
import api.catalogo.produtos.infra.persistence.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import static org.mockito.Mockito.*;

public class ImportacaoJobConfigurationTest {

    private JobLauncher jobLauncher;
    private Job job;
    private PlatformTransactionManager transactionManager;
    private ImportacaoJobConfiguration importacaoJobConfiguration;
    private JobRepository jobRepository;
    private DataSource dataSource;
    private ProdutoEntity produtoEntity;

    @BeforeEach
    void setUp() {

        jobLauncher = mock(JobLauncher.class);
        job = mock(Job.class);
        jobRepository = mock(JobRepository.class);
        dataSource = mock(DataSource.class);
        transactionManager = mock(PlatformTransactionManager.class);
        produtoEntity = mock(ProdutoEntity.class);
        importacaoJobConfiguration = mock(ImportacaoJobConfiguration.class);

    }

    @Test
    void reader() {
        when(importacaoJobConfiguration.reader()).thenReturn(new FlatFileItemReaderBuilder<ProdutoEntity>()
                .name("leitura-csv")
                .resource(new FileSystemResource("files/catalogo.csv"))
                .comments("--")
                .delimited()
                .delimiter(";")
                .names("nome", "tipo", "descricao", "valor", "quantidadeEstoque", "quantidadeReservada", "horaImportacao")
                .fieldSetMapper(new ImportacaoMapper())
                .build());

    }

    @Test
    void write() {
        when(importacaoJobConfiguration.writer(dataSource)).thenReturn(new JdbcBatchItemWriterBuilder<ProdutoEntity>()
                .dataSource(dataSource)
                .sql("INSERT INTO produto (nome, tipo, descricao, valor, quantidade_estoque, quantidade_reservada, hora_importacao) VALUES" +
                        "(:nome, :tipo, :descricao, :valor, :quantidadeEstoque, :quantidadeReservada , :horaImportacao)"
                )
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build());
    }


}
