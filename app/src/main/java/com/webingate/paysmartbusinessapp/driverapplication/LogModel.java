package com.webingate.paysmartbusinessapp.driverapplication;

public class LogModel {

    private String member_name;
    private String id;
    private String status;
    private String contactType;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    private String active;

    /* public LogModel(String member_name, int id, String status,
                     String contactType) {

      this.member_name = member_name;
      this.id = id;
      this.status = status;
      this.contactType = contactType;
     }
*/
    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

}