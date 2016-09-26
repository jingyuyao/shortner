package com.jingyuyao.shortener.resources;

import com.jingyuyao.shortener.api.CreateLink;
import com.jingyuyao.shortener.core.Link;
import com.jingyuyao.shortener.db.LinkDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.Validator;
import javax.ws.rs.core.Response;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// TODO: Test failure cases
public class LinkResourceTest {
    private static final String URL = "http://www.example.org";

    @Mock
    private Validator validator;
    @Mock
    private LinkDAO dao;
    @Mock
    private List<Link> links;
    @Mock
    private CreateLink createLink;
    @Captor
    private ArgumentCaptor<Link> linkCaptor;

    private LinkResource resource;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(validator.validate(any(Link.class))).thenReturn(Collections.emptySet());

        resource = new LinkResource(validator, dao);
    }

    @Test
    public void getLinks() {
        when(dao.findAll()).thenReturn(links);

        Response response = resource.getLinks();

        assertThat(response.getEntity()).isEqualTo(links);
    }

    @Test
    public void createLink() {
        when(createLink.getUrl()).thenReturn(URL);

        Response response = resource.createLink(createLink);

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(dao).save(linkCaptor.capture());
        assertThat(linkCaptor.getValue().getUrl()).isEqualTo(URL);
        assertThat(linkCaptor.getValue().getVisits()).isZero();
    }
}
