import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        // this implementation is intentionally simple: it removes any commas
        // or newlines from the body so that the resulting file has a single
        // row with two columns. callers must be aware that the exported text
        // is not a faithful copy of the original body.
        String body = req.body.replace("\n", " ").replace(",", " ");
        String csv = "title,body\n" + req.title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
