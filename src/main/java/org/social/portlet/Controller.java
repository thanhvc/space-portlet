package org.social.portlet;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.social.core.space.spi.SpaceService;
import juzu.Path;
import juzu.Resource;
import juzu.SessionScoped;
import juzu.View;
import juzu.impl.plugin.application.ApplicationContext;
import juzu.plugin.ajax.Ajax;
import juzu.request.RequestContext;
import org.social.portlet.models.SpaceBean;
import org.social.portlet.utils.Utils;
import org.social.portlet.qualifiers.Current;
import org.social.portlet.templates.spaces;
import org.social.portlet.templates.index;

public class Controller {

 
  @Inject SpaceService spaceService;
  
  @Inject @Current @SessionScoped Identity currentUser;
  
  
  @Inject @Path("index.gtmpl") index index;
  @Inject @Path("spaces.gtmpl") spaces spaces;

  @View
  public void index() throws Exception {
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
