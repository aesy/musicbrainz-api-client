package org.easy.musicbrainz.entity.metadata;

import org.easy.musicbrainz.entity.collection.ArtistList;
import org.easy.musicbrainz.entity.formatting.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "metadata", namespace = "http://musicbrainz.org/ns/mmd-2.0#")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArtistMetadata {

    @XmlAttribute(name = "created")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date created;

    @XmlElement(name = "artist-list")
    public ArtistList artists;

}
