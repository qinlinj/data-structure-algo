package org.qinlinj.practical.underground;

public class StartEnd {
    private String start;
    private String end;

    public StartEnd(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
    }
}
