package pl.edu.mas.s16941.mp3.model.dynamic;

import pl.edu.mas.s16941.mp3.exception.ClientTypeException;
import pl.edu.mas.s16941.mp3.exception.ModelCreationException;

public class Client implements StandardClient, VIPClient {
    private ClientType type;
    private String name, surname;
    private Integer clientScore;    // for standard client
    private String VIPScore;       // for VIP client

    public Client(String name, String surname, ClientType type, int clientScore, String VIPScore) throws ModelCreationException, ClientTypeException {
        setName(name);
        setSurname(surname);
        setType(type);
        if (isVIP()) {
            setVIPScore(VIPScore);
        } else {
            setClientScore(clientScore);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ModelCreationException {
        if (name == null || name.isEmpty()) throw new ModelCreationException("Name of client cannot be null or empty.");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws ModelCreationException {
        if (surname == null || surname.isEmpty())
            throw new ModelCreationException("Surname of client cannot be null or empty.");
        this.surname = surname;
    }

    public ClientType getTypes() {
        return type;
    }

    private void setType(ClientType type) throws ModelCreationException {
        if (type == null)
            throw new ModelCreationException("Types of tires cannot be null or empty");
        this.type = type;
    }

    @Override
    public int getClientScore() throws ClientTypeException {
        if (isVIP()) throw new ClientTypeException("Cannot get standard score for VIP client");
        return clientScore;
    }

    @Override
    public void setClientScore(int clientScore) throws ModelCreationException, ClientTypeException {
        if (isVIP()) throw new ClientTypeException("Cannot set VIP score for standard client");
        if (clientScore > 5 || clientScore < 0)
            throw new ModelCreationException("Standard client score has to be in range 0 - 5");
        this.clientScore = clientScore;
    }

    @Override
    public String getVIPScore() throws ClientTypeException {

        if (!isVIP()) throw new ClientTypeException("Cannot get VIP score for standard client");
        return VIPScore;
    }

    @Override
    public void setVIPScore(String VIPScore) throws ModelCreationException, ClientTypeException {
        if (!isVIP()) throw new ClientTypeException("Cannot set standard score for VIP client");
        if (VIPScore == null || VIPScore.isEmpty())
            throw new ModelCreationException("VIPScore of VIP client cannot be null or empty.");
        this.VIPScore = VIPScore;
    }

    private void resetVIPScore() {
        this.VIPScore = null;
    }

    private void resetStandardScore() {
        this.clientScore = null;
    }

    public void changeToVIP(String VIPScore) throws ModelCreationException, ClientTypeException {
        if (isVIP()) return;
        resetStandardScore();
        setType(ClientType.VIP);
        setVIPScore(VIPScore);

    }

    public void changeToStandard(int standardScore) throws ModelCreationException, ClientTypeException {
        if (!isVIP()) return;
        resetVIPScore();
        setType(ClientType.STANDARD);
        setClientScore(standardScore);
    }

    public boolean isVIP() {
        return type.equals(ClientType.VIP);
    }

    @Override
    public String toString() {
        if (isVIP()) {
            return "Client{" +
                    "type=" + type +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", VIPScore='" + VIPScore + '\'' +
                    '}';
        } else {
            return "Client{" +
                    "type=" + type +
                    ", name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", clientScore='" + clientScore + '\'' +
                    '}';
        }
    }
}
