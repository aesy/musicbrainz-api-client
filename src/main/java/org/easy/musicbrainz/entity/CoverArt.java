package org.easy.musicbrainz.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cover-art-archive", namespace = "http://musicbrainz.org/ns/mmd-2.0#")
public class CoverArt {

    @XmlElement(name = "artwork")
    public boolean artwork;

    @XmlElement(name = "count")
    public int count;

    @XmlElement(name = "front")
    public boolean front;

    @XmlElement(name = "back")
    public boolean back;

}
