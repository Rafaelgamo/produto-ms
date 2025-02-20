package api.catalogo.produtos.infra.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImportacaoController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    @Qualifier("batchTransactionManager")
    private PlatformTransactionManager batchTransactionManager;

    @Operation(description = "importação da pasta files")
    @GetMapping("/importar")
    public void importaArquivo() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        TransactionTemplate transactionTemplate = new TransactionTemplate(batchTransactionManager);
        transactionTemplate.execute(status -> {
            try {
                JobParameters jobParameters = new JobParameters();
                jobLauncher.run(job, jobParameters);
            } catch (Exception e) {
                throw new RuntimeException("Failed to run batch job", e);
            }
            return null;
        });

    }
}
