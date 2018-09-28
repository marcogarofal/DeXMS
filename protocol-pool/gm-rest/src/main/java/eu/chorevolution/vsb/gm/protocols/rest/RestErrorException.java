package eu.chorevolution.vsb.gm.protocols.rest;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RestErrorException")
public class RestErrorException {

    @XmlElement(name = "error", required = true)
    private String error;

    public String getError() {
        return error;
    }

    public void setlatitude(String error) {
        this.error = error;
    }

}