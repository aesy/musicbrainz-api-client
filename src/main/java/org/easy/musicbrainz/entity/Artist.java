package org.easy.musicbrainz.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "artist")
@JsonIgnoreProperties(ignoreUnknown = true) // TODO remove
public class Artist implements Entity {

    @XmlEnum
    public enum Type {
        @XmlEnumValue("Person")    PERSON,
        @XmlEnumValue("Group")     GROUP,
        @XmlEnumValue("Orchestra") ORCHESTRA,
        @XmlEnumValue("Choir")     CHOIR,
        @XmlEnumValue("Character") CHARACTER,
        @XmlEnumValue("Other")     OTHER
    }

    @XmlEnum
    public enum Gender {
        @XmlEnumValue("male")   MALE,
        @XmlEnumValue("female") FEMALE,
        @XmlEnumValue("other")  OTHER
    }

    public static class Area {

        @XmlAttribute(name = "id")
        public String id;

        @XmlElement(name = "name")
        public String name;

        @XmlElement(name = "sort-name")
        public String sortName;

    }

    public static class LifeSpan {

        @XmlElement(name = "begin")
        // @XmlJavaTypeAdapter(DateAdapter.class)
        public Date begin;

        @XmlElement(name = "end")
        // @XmlJavaTypeAdapter(DateAdapter.class)
        public Date end;

        @XmlElement(name = "ended")
        public boolean ended;

    }

    public static class IpiList {

        @XmlAttribute(name = "ipi")
        public String ipi;

    }

    @XmlAttribute(name = "id")
    public String id;

    @XmlAttribute(name = "type")
    public Type type;

    @XmlAttribute(name = "score", namespace = "http://musicbrainz.org/ns/ext#-2.0")
    public Integer score;

    // alias

    @XmlElement(name = "name")
    public String name;

    @XmlElement(name = "sort-name")
    public String sortName;

    @XmlElement(name = "gender")
    public Gender gender;

    @XmlElement(name = "life-span")
    public LifeSpan lifeSpan;

    @XmlElement(name = "country")
    public String country;

    @XmlElement(name = "area")
    public Area area;

    @XmlElement(name = "begin-area")
    public Area beginArea;

    @XmlElement(name = "end-area")
    public Area endArea;

    @XmlElement(name = "ipi-list")
    public IpiList ipiList;

    // alias-list

    @XmlElement(name = "disambiguation")
    public String disambiguation;

    // @XmlElement(name = "recording-list")
    // public RecordingList recordings;
    //
    // @XmlElement(name = "release-group-list")
    // public ReleaseGroupList releasegroups;
    //
    // @XmlElement(name = "release-list")
    // public ReleaseList releaselists;
    //
    // @XmlElement(name = "relation-list")
    // public RelationList relationlists;
    //
    // @XmlElement(name = "work-list")
    // public WorkList works;
    //
    // @XmlElement(name = "tag-list")
    // public TagList tags;

}
