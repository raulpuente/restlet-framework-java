/*
 * Copyright � 2005 J�r�me LOUVEL.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package com.noelios.restlet;

import java.io.IOException;

import org.restlet.Maplet;
import org.restlet.RestletCall;
import org.restlet.Factory;
import org.restlet.Manager;
import org.restlet.UniformCall;
import org.restlet.component.RestletContainer;
import org.restlet.component.RestletServer;
import org.restlet.data.CookieSetting;
import org.restlet.data.Form;
import org.restlet.data.Reference;
import org.restlet.data.Representation;

import com.noelios.restlet.component.RestletContainerImpl;
import com.noelios.restlet.component.RestletServerImpl;
import com.noelios.restlet.data.CookieSettingImpl;
import com.noelios.restlet.data.FormImpl;
import com.noelios.restlet.data.ReferenceImpl;

/**
 * Noelios Restlet Engine. Also acts as a factory implementation.
 */
public class Engine implements Factory
{
   /**
    * Registers the Noelios Restlet Engine
    */
   public static void register()
   {
      Manager.registerFactory(new Engine());
   }

   /**
    * Creates a delegate restlet server.
    * @param parent The parent restlet server.
    * @param name The server's name.
    * @return The new restlet server.
    */
   public RestletServer createRestletServer(RestletServer parent, String name)
   {
      return new RestletServerImpl(name);
   }

   /**
    * Creates a delegate restlet container.
    * @param parent The parent restlet container.
    * @param name The container's name.
    * @return The new restlet container.
    */
   public RestletContainer createRestletContainer(RestletContainer parent, String name)
   {
      return new RestletContainerImpl(parent, name);
   }

   /**
    * Creates a delegate maplet.
    * @param parent The parent maplet.
    * @param container The restlet container.
    * @return A new maplet.
    */
   public Maplet createMaplet(Maplet parent, RestletContainer container)
   {
      return new MapletImpl(parent, container);
   }

   /**
    * Returns a new restlet call wrapping a given uniform call. Developers who need to extend the default
    * restlet calls should override it.
    * @param call The uniform call to wrap.
    * @return A new restlet call.
    */
   public RestletCall createRestletCall(UniformCall call)
   {
      return new RestletCallImpl(call);
   }

   /**
    * Returns a new cookie setting.
    * @param name The name.
    * @param value The value.
    * @return A new cookie setting.
    */
   public CookieSetting createCookieSetting(String name, String value)
   {
      return new CookieSettingImpl(name, value);
   }

   /**
    * Creates a new form able to process the given form content.
    * @param content The form content to process.
    * @return A new form with the given content.
    */
   public Form createForm(Representation content) throws IOException
   {
      return new FormImpl(content);
   }

   /**
    * Creates a new reference from a URI reference.
    * @param uriReference The URI reference.
    * @return The new URI reference.
    */
   public Reference createReference(String uriReference)
   {
      return new ReferenceImpl(uriReference);
   }

   /**
    * Creates a new uniform call.
    * @return A new uniform call.
    */
   public UniformCall createCall()
   {
      return new UniformCallImpl();
   }

}
