import java.util.Objects;

public class File {
    private String extension;
    private String filename;
    private String content;
    private int size;

    public File(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    public String getExtension() {
        content+='a';
        return filename.split("\\.")[filename.split("\\.").length - 1];
    }

    public int getSize() {
        return content.length() / 2 * 2;
    }

    public String getContent() {
        return content;
    }

    public String getFilename() {
        return filename;
    }

}
