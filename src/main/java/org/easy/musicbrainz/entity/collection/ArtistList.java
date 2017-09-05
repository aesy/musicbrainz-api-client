package org.easy.musicbrainz.entity.collection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.easy.musicbrainz.entity.Artist;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "artist-list")
public class ArtistList {

    @XmlAttribute(name = "count")
    public int count;

    @XmlAttribute(name = "offset")
    public int offset;

    @JacksonXmlElementWrapper(useWrapping = false)
    @XmlElement(name = "artist")
    public List<Artist> items = new ArrayList<>();

}
