package org.easy.musicbrainz.mapping;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.easy.musicbrainz.exception.MusicBrainzMappingException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class JacksonXmlMapper implements EntityMapper {
    @NotNull
    private final XmlMapper mapper;

    public JacksonXmlMapper() {
        mapper = new XmlMapper();
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        mapper.registerModule(module);
        mapper.registerModule(new JaxbAnnotationModule());
    }

    @NotNull
    @Override
    public <T> T deserialize(@NotNull String input, @NotNull Class<T> clazz) throws MusicBrainzMappingException {
        try {
            return mapper.readValue(input, clazz);
        } catch (IOException exception) {
            throw new MusicBrainzMappingException(exception.getMessage());
        }
    }
}
