package it.unibo.ddd.acl.factory.impl;

import it.unibo.ddd.acl.Row;
import it.unibo.ddd.acl.Table;
import it.unibo.ddd.acl.factory.TableFactory;
import it.unibo.ddd.acl.impl.RowImpl;
import it.unibo.ddd.acl.impl.TableImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TableFactoryImpl implements TableFactory {

    @Override
    public Table fromRows(Row header, Iterable<Row> rows) {
        new TableImpl(header, rows);
    }

    @Override
    public Table fromCsv(Reader fileReader) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build();
        List<String> header = Arrays.stream(csvFormat.getHeader()).toList();
        Iterable<CSVRecord> records = csvFormat.parse(fileReader);

        List<Row> rows = new ArrayList<>();
        for (CSVRecord record : records) {
            rows.add(new RowImpl(record.stream().toList()));
        }
        return new TableImpl(new RowImpl(header), rows);
    }
}
