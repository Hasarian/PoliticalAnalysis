package be.demoustiez.politicalAnalysisAPI.model;




import java.util.GregorianCalendar;
import java.util.List;

public class Event {
    private Integer id;
    private Commission commission;
    private String subject;
    private GregorianCalendar date;
    private boolean eventPodcast;
    private List<Publication> eventPublications;

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(String dateDay, String hourMinute) {
        date = new GregorianCalendar();
        String[] dateElements = dateDay.split("/");
        String[] hourElement=hourMinute.split(":");
        date.set(Integer.parseInt(dateElements[2]),Integer.parseInt(dateElements[1]),Integer.parseInt(dateElements[0]),
                Integer.parseInt(hourElement[1]),Integer.parseInt(hourElement[0]));

    }
    public void setDate(GregorianCalendar calendar){
        this.date=calendar;
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
