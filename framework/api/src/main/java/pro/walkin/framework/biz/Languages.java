package pro.walkin.framework.biz;

public enum Languages {
    CN("zh-CN"), EN("en-US");

    private final String value;

    Languages(String s) {
        this.value = s;
    }

    public static Languages of(String language) {
        for (Languages value : Languages.values()) {
            if (value.value.equals(language)) {
                return value;
            }
        }
        throw new IllegalArgumentException("");
    }

    @Override
    public String toString() {
        return value;
    }
}
