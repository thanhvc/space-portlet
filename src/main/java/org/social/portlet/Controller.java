package org.social.portlet;

import org.social.portlet.models.SpaceBean;
import org.social.portlet.qualifiers.Current;
import org.social.portlet.utils.Utils;
import org.exoplatform.social.core.activity.model.ExoSocialActivity;
import org.exoplatform.social.core.activity.model.ExoSocialActivityImpl;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.service.LinkProvider;
import org.exoplatform.web.application.RequestContext;
import org.exoplatform.commons.utils.ListAccess;

import juzu.Action;
import juzu.Path;
import juzu.Resource;
import juzu.Response;
import juzu.SessionScoped;
import juzu.View;
import juzu.plugin.ajax.Ajax;
import juzu.template.Template;
import org.social.portlet.templates.index;
import org.social.portlet.templates.spaces;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/** @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a> */
public class Controller {
  @Inject SpaceService spaceService;
  
  @Inject @Path("index.gtmpl")    index index;
  @Inject @Path("spaces.gtmpl")    spaces spaces;

  @Inject @SessionScoped Identity currentUser;

  @View
  public void index() throws IOException, Exception  {
    index.render();
  }

  @Ajax
  @Resource
  public void loadSpaces() throws Exception {
    ListAccess<Space> spaceListAccess = spaceService.getAccessibleSpacesWithListAccess(currentUser.getRemoteId());
    List<Space> got = new ArrayList<Space>(Arrays.asList(spaceListAccess.load(0, 100)));
    
    List<SpaceBean> spaceList = Utils.fillSpacesURI(got);
    spaces.with().spaces(spaceList).render();
    
  }

}
