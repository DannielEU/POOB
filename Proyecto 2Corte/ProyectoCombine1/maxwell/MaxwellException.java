package maxwell;
public class MaxwellException extends Exception
{
    public static final String OUTOFRANGE = "Particle Out Of Range.";
    public static final String DIMENSIONESERROR = "Dimension Invalid.";
    public static final String PARTICLEINVALID = "There are more than 50 particles.";
    public static final String NOTEXISTPARTICLE = "There are no particles.";
    public static final String NOTEXISTDEMON = "There are no demons.";
    public static final String TIMENEGATIVE = "Time can not be negative.";
    public static final String DEAMONINVALID = " The Demon´s position is invalid";
    public MaxwellException(String e){
        super(e);
    }
}
