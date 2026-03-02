/**
 * Base exporter class. Concrete subclasses must implement {@link #doExport}
 * and can rely on a few guarantees:
 *
 * <ul>
 *   <li>the request object passed to {@code doExport} will never be null;</n *       both {@code title} and {@code body} fields will be non-null (empty
 *       string if necessary).</li>
 *   <li>implementations may throw {@link IllegalArgumentException} if they are
 *       unable to handle particular content (for example PDF has a hard limit
 *       on body length). callers of {@link #export} should be prepared to
 *       catch runtime exceptions.</li>
 *   <li>implementations are free to transform the input data (e.g. remove
 *       commas or newlines for CSV) but such behaviour must be clearly
 *       documented by that subclass; callers should not assume that the
 *       original text is preserved verbatim.</li>
 *   <li>the result returned by {@code export} must be non-null and its
 *       {@code bytes} field must be non-null as well.</li>
 * </ul>
 */
public abstract class Exporter {
    // public entry point that applies the simple normalisations described above
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        String title = req.title != null ? req.title : "";
        String body = req.body != null ? req.body : "";
        return doExport(new ExportRequest(title, body));
    }

    /**
     * Perform the actual formatting to the requested media type. The passed
     * request is guaranteed to be non-null and to contain non-null strings.
     */
    protected abstract ExportResult doExport(ExportRequest req);
}
