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
package org.social.portlet.models;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Mar 14, 2012  
 */
public class SpaceBean {

  private String displayName;
  private String url;
  private String avatarUrl;
  private String description;
  
  public SpaceBean(String displayName, String url, String avatarUrl, String description) {
    
    this.displayName = displayName;
    this.url = url;
    this.avatarUrl = avatarUrl;
    this.description = description;
  }
  
  public String getDisplayName() {
    return displayName;
  }

  public String getUrl() {
    return url;
  }
  
  public String getAvatarUrl() {
    return avatarUrl;
  }

  public String getDescription() {
    return description;
  }
}