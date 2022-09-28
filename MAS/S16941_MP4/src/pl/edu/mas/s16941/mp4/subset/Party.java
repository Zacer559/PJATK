package pl.edu.mas.s16941.mp4.subset;

import pl.edu.mas.s16941.mp4.exception.ModelValidationException;

public class Party extends ObjectPlus4 {
    private String name;

    public Party(String name) throws ModelValidationException {
        super();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelValidationException {
        if (name == null || name.isEmpty()) throw new ModelValidationException("Name of party cannot be null or empty");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "name='" + name + '\'' +
                '}';
    }

    public static final String roleParticipantsOf = "Participants of";
    public static final String roleOrganizedBy = "Organized by";

}
