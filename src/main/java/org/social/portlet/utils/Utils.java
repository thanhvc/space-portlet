/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.social.portlet.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.commons.collections.map.HashedMap;
import org.exoplatform.commons.utils.Safe;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.social.core.service.LinkProvider;
import org.exoplatform.social.core.space.model.Space;
import org.exoplatform.web.WebAppController;
import org.exoplatform.web.controller.QualifiedName;
import org.exoplatform.web.controller.metadata.ControllerDescriptor;
import org.exoplatform.web.controller.metadata.DescriptorBuilder;
import org.exoplatform.web.controller.router.Router;
import org.exoplatform.web.controller.router.RouterConfigException;
import org.exoplatform.web.controller.router.URIWriter;
import org.juzu.impl.application.InternalApplicationContext;
import org.juzu.request.RequestContext;
import org.social.portlet.models.SpaceBean;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Mar 14, 2012  
 */
public class Utils {
  
  private static final QualifiedName PATH = QualifiedName.create("gtn", "path");
  /**
   * Qualified name site type for rendering url.
   * 
   * @since 1.2.2
   */
  private static final QualifiedName REQUEST_SITE_TYPE = QualifiedName.create("gtn", "sitetype");
  
  /**
   * Qualified name handler for rendering url.
   * 
   * @since 1.2.2
   */
  private static final QualifiedName REQUEST_HANDLER = QualifiedName.create("gtn", "handler");
  
  /**
   * Qualified name site name for rendering url.
   * 
   * @since 1.2.2
   */
  private static final QualifiedName REQUEST_SITE_NAME = QualifiedName.create("gtn", "sitename");
  
  private static final QualifiedName LANG = QualifiedName.create("gtn", "lang");
  
  /**
   * 
   * @param mySpaces
   * @return
   */
  public static List<SpaceBean> fillSpacesURI(List<Space> mySpaces) {
    try {
      
      Router router = getRouter(getConfigurationPath());
      Map<QualifiedName, String> qualifiedName = new HashedMap();
      qualifiedName.put(REQUEST_HANDLER, "portal");
      qualifiedName.put(REQUEST_SITE_TYPE, "group");
      
      
      List<SpaceBean> result = new ArrayList<SpaceBean>();
      String avatarUrl = "";
      String spaceUrl = "";
      for (Space space : mySpaces) {
        StringBuilder urlBuilder = new StringBuilder();
        qualifiedName.put(REQUEST_SITE_NAME, space.getGroupId());
        qualifiedName.put(PATH, space.getUrl());
        router.render(qualifiedName, new URIWriter(urlBuilder));
        avatarUrl = space.getAvatarUrl() == null ? LinkProvider.SPACE_DEFAULT_AVATAR_URL : space.getAvatarUrl();
        spaceUrl = "/" + PortalContainer.getCurrentPortalContainerName() + urlBuilder.toString();
        result.add(new SpaceBean(space.getDisplayName(), spaceUrl, avatarUrl, space.getDescription()));
      }
      
      return result;
    } catch (Exception e) {
      throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
    }
  }
  
  /**
   * Gets the configuration path of file controller.xml
   * 
   * @return
   * @since 1.2.2
   */
  private static String getConfigurationPath() {
    WebAppController webAppController = (WebAppController) PortalContainer.getInstance().getComponentInstanceOfType(WebAppController.class);
    return webAppController.getConfigurationPath();
  }
  
  /**
   * Gets the router from path of file controller.xml
   * 
   * @param path
   * @return
   * @throws IOException
   * @throws RouterConfigException
   * @since 1.2.2
   */
  private static Router getRouter(String path) throws IOException, RouterConfigException {
     File f = new File(path);
     if (!f.exists()) {
        throw new MalformedURLException("Could not resolve path " + path);
     }
     if (!f.isFile()) {
        throw new MalformedURLException("Could not resolve path " + path + " to a valid file");
     }
     return getRouter(f.toURI().toURL());
  }
  
  /**
   * Gets the router from url.
   * 
   * @param url
   * @return
   * @throws RouterConfigException
   * @throws IOException
   * @since 1.2.2
   */
  private static Router getRouter(URL url) throws RouterConfigException, IOException {
     InputStream in = url.openStream();
     try {
        ControllerDescriptor routerDesc = new DescriptorBuilder().build(in);
        return new Router(routerDesc);
     } finally {
        Safe.close(in);
     }
  }
    
  
}
