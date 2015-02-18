package ch.heigvd.res.lab00;

interface IInstrument {
    public String play();
    public int getSoundVolume();
    public String getColor();
}
class Trumpet implements IInstrument {
	Trumpet() {}

	public String play() {return "pouet";}

	public int getSoundVolume() { return 10; }

	public String getColor() { return "golden"; }
}

class Flute implements IInstrument {
	Flute() {}

	public String play() {return "";}
	public int getSoundVolume() { return 2; }
	public String getColor() { return "brown"; }
}


/**
 * This is a very simple class used to demonstrate the specify-implement-validate
 * cycle. All methods used in the JUnit test are defined, so the test class will
 * compile. There is a bug in the add method, so one of the tests is expected
 * to fail.
 * 
 * If you look at the class named ch.heigvd.res.lab01.ApplicationTest, you will
 * find an executable specification for this class. The test methods specify
 * the expected behavior for this class.
 * 
 * @author Olivier Liechti
 */
public class Application {

  private String message;

  public Application() {
    this("HEIG-VD rocks!");
  }

  public Application(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public int add(int a, int b) {
    return a + b;
  }

}
