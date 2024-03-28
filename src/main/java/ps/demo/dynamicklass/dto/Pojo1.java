package ps.demo.dynamicklass.dto;

import java.util.Date;

public class Pojo1 {

    private int id;
    private String name;
    private Double score;
    private Date at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "Pojo1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", at=" + at +
                '}';
    }

    public String print() {
        System.out.println(this);
        return this.toString();
    }

    public String validate() {
        StringBuilder sb = new StringBuilder();
        if (id == 0) {
            sb.append("id is invalid");
            sb.append("\n");
        }
        if (name == null || name.trim().equals("")) {
            sb.append("name is invalid");
            sb.append("\n");
        }
        if (at == null) {
            sb.append("at is invalid");
            sb.append("\n");
        }
        return sb.toString();
    }
}
