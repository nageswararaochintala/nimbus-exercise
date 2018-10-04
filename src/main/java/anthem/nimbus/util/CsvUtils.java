package anthem.nimbus.util;

import com.github.trang.copiers.Copiers;
import com.github.trang.copiers.inter.Copier;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.csv.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.*;

/**
 * CSV
 *
 * @author Nageswara rao
 */
@Slf4j
public class CsvUtils {

    @SneakyThrows(IOException.class)
    public static List<String[]> toList(Path file) {
        try (InputStream is = Files.newInputStream(file, READ)) {
            CsvParserSettings settings = Csv.parseExcel();
            settings.setHeaderExtractionEnabled(true);
            CsvParser parser = new CsvParser(settings);
            return parser.parseAll(is);
        }
    }

    @SneakyThrows(IOException.class)
    public static <T> List<T> toList(Path file, Class<T> beanClass) {
        try (InputStream is = Files.newInputStream(file, READ)) {
          
            BeanListProcessor<T> beanProcessor = new BeanListProcessor<>(beanClass);
            
            CsvParserSettings settings = Csv.parseExcel();
            
            settings.setProcessor(beanProcessor);
            
            settings.setHeaderExtractionEnabled(true);
            settings.setProcessorErrorHandler((error, inputRow, context) -> {
                log.warn("parse error, value: {}, lineIndex: {}, columnName: {}, columnIndex: {}",
                        error.getValue(), error.getLineIndex(), error.getColumnName(), error.getColumnIndex());
            });
            CsvParser parser = new CsvParser(settings);
           
            parser.parse(is);
            return beanProcessor.getBeans();
        }
    }

    /**
     * CSV
     *
     * @param file        
     * @param sourceClass Bean
     * @param targetClass Bean
     * @return beanList
     */
    @SneakyThrows(IOException.class)
    public static <F, T> List<T> toList(Path file, Class<F> sourceClass, Class<T> targetClass) {
        try (InputStream is = Files.newInputStream(file, READ)) {
            
            BeanListProcessor<F> beanProcessor = new BeanListProcessor<>(sourceClass);
            
            CsvParserSettings settings = Csv.parseExcel();
            
            settings.setProcessor(beanProcessor);
           
            settings.setHeaderExtractionEnabled(true);
            CsvParser parser = new CsvParser(settings);
            
            try (InputStreamReader reader = new InputStreamReader(is)) {
                
                parser.parse(reader);
            }
            Copier<F, T> copier = Copiers.create(sourceClass, targetClass);
            return beanProcessor.getBeans().parallelStream().map(copier::copy).collect(Collectors.toList());
        }
    }

    /**
     *
     * @param file       
     * @param sourceClass
     * @param targetClass 
     * @param sourceList 
     */
    @SneakyThrows(IOException.class)
    public static <F, T> void toFile(Path file, Class<F> sourceClass, Class<T> targetClass, List<F> sourceList) {
        if (Files.notExists(file)) {
            Files.createFile(file);
        }
        Copier<F, T> copier = Copiers.create(sourceClass, targetClass);
        List<T> data = sourceList.parallelStream().map(copier::copy).collect(Collectors.toList());
       
        BeanWriterProcessor<T> writerProcessor = new BeanWriterProcessor<>(targetClass);
        writerProcessor.setStrictHeaderValidationEnabled(true);
        
        CsvWriterSettings settings = Csv.writeExcel();
       
        settings.setRowWriterProcessor(writerProcessor);
        try (OutputStream os = Files.newOutputStream(file, CREATE, TRUNCATE_EXISTING, WRITE);
             OutputStreamWriter osw = new OutputStreamWriter(os)) {
            CsvWriter writer = new CsvWriter(osw, settings);
            writer.processRecordsAndClose(data);
        }
    }

}