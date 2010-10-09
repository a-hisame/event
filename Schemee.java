package nagoyahackathon;

public class Schemee {
  private final String in;
  public Schemee(String in) {
    this.in = in;
  }
  
  public String run() {
    return String.format("<Schemee>%s</Schemee>", in);
  }
}
