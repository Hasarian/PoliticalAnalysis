package be.demoustiez.politicalAnalysisAPI.model;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Event {
    private Integer id;
    private Commission commission;
    private String subject;
    private Date date;
    private boolean eventPodcast;
    private List<Publication> eventPublications;

    public Date getDate() {
        return date;
    }

    public void setDate(String dateDay, String hourMinute) throws ParseException{
        String dateString = dateDay.concat("/"+hourMinute);
        this.setDate(new SimpleDateFormat("dd/MM/yyyy/HH:mm").parse(dateString));
    }
    public void setDate(Date localDate){
        this.date=localDate;
    }

    public boolean isEventPodcast() {
        return eventPodcast;
    }

    public void setEventPodcast(boolean eventPodcast) {
        this.eventPodcast = eventPodcast;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Publication> getEventPublications() {
        return eventPublications;
    }

    public void setEventPublications(List<Publication> eventPublications) {
        this.eventPublications = eventPublications;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }
}
