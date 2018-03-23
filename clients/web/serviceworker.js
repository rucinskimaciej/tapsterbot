  /*
  MIT License
  Copyright (c) 2016-2018 Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.
  */
  /*
  ✿✿✿✿ ʕ •ᴥ•ʔ/ ︻デ═一
  */

  /**
  * @file serviceworker.js
  * @brief A service worker to use as a cache system in case of missing Internet / data / network connection.
  *       Please note that you must serve this web app through HTTPS or localhost so as to use Service Workers.
  * @author pylapp
  * @version 1.0.0
  * @since 23/03/2018
  */

  "use strict";

  let CACHE_NAME = 'tapser2webclient-cache-v1';
  let urlsToCache = [

    '/',

    /* Logic scripts */

    /* Pictures assets */
    '/pictures/favicon.png',
    '/pictures/logo-x96.png',
    '/pictures/logo-x144.png',
    '/pictures/logo-x192.png',

    /* Style sheets */
    '/styles/global.css',
    '/styles/media.css',
    '/styles/header.css',
    '/styles/console.css',
    '/styles/commands.css'

  ];

  /**
  * When the Service Worker in installing
  */
  self.addEventListener('install', function(event) {
    event.waitUntil(
      caches.open(CACHE_NAME).then(function(cache) {
        console.log('Service Worker: opened cache');
        return cache.addAll(urlsToCache);
      })
    );
  });

  /**
  * When the Service Worker is fetching resources
  */
  self.addEventListener('fetch', function(event) {
    event.respondWith(
      caches.match(event.request).then(function(response) {
        console.log('Service Worker: fetch something - '+event.request);
        // Cache hit - return response
        if (response) {
          return response;
        }
        // Clone it to consume the request 2 times (by cache and by browser)
        let fetchRequest = event.request.clone();
        console.log('Service Worker: fetch something - '+fetchRequest);
        return fetch(fetchRequest).then(
          function(response) {
            // Check if we received a valid response
            if(!response || response.status !== 200 || response.type !== 'basic') {
              console.log('Service Worker: fetch response '+response);
              return response;
            }
            // Clone it to consume the reponse 2 times (by cache and by browser)
            var responseToCache = response.clone();
            caches.open(CACHE_NAME).then(function(cache) {
              console.log('Service Worker: reposne to cache - '+responseToCache);
              cache.put(event.request, responseToCache);
            });
            return response;
          } // End of function(response)
        ); // End of return fetch(fetchRequest).then
      })
    ); // End of event.respondWith
  });
