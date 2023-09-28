package it.unibo.ddd.acl.factory;

import it.unibo.ddd.acl.Row;
import it.unibo.ddd.acl.Table;

import java.io.IOException;
import java.io.Reader;

public interface TableFactory {

    Table fromRows(Row header, Iterable<Row> rows);

    Table fromCsv(Reader fileReader) throws IOException;

}
