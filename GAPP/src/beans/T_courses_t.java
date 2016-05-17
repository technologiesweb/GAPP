package beans;

public class T_courses_t {

    private Long      CT_id;
    private String    CT_name;
    private String    CT_description;

    public Long getCT_id() {
        return CT_id;
    }
    public void setCT_id(Long CT_id) {
        this.CT_id = CT_id;
    }

    public void setCT_name(String CT_name) {
        this.CT_name = CT_name;
    }
    public String getCT_name() {
        return CT_name;
    }

    public void setCT_description(String CT_description) {
        this.CT_description = CT_description;
    }
    public String getCT_description() {
        return CT_description;
    }
}
