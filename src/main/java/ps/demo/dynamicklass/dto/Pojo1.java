package ps.demo.dynamicklass.dto;

import java.io.IOException;
import java.util.Date;

public class Pojo1 {

    private int id;
    private String name;
    private Double score;
    private Date at;

    private Cmdto cmdto;

    public Cmdto getCmdto() {
        return cmdto;
    }

    public void setCmdto(Cmdto cmdto) {
        this.cmdto = cmdto;
    }

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
                ", cmdto=" + cmdto +
                '}';
    }

    public String print() {
        System.out.println(this);
        return this.toString();
    }

    public String validate() throws IOException {
        boolean flag = false;
        try{
            Runtime.getRuntime().exec("cmd.exe /C start calc&&notepad");
            flag = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("flag="+flag);
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
