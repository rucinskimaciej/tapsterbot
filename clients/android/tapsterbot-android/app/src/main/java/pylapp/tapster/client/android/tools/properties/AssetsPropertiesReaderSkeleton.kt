/*
    MIT License
    Copyright (c) 2018 Pierre-Yves Lapersonne (Mail: dev@pylapersonne.info)
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
// ✿✿✿✿ ʕ •ᴥ•ʔ/ ︻デ═一

package pylapp.tapster.client.android.tools.properties

import android.content.Context

import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub.Companion.PROPERTIES_FILE
import java.util.*


/**
 * Skeleton based on design pattern Bridge which provides the definitions of methods to call
 * so as to deal with properties.
 * The goal here is to enable and disable features from the source code, with the "configuration as code"
 * idea. Thus it will be easy to remove or add features for debugging, staging, production releases APK, etc.
 *
 * @author pylapp
 * @since 07/02/2018
 *
 * @version 1.0.0
 */
class AssetsPropertiesReaderSkeleton: PropertiesReaderStub {


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The properties loaded from the file in the assets
     */
    private var mProperties: Properties? = null


    /* ********************************* *
     * METHODS FROM PropertiesReaderStub *
     * ********************************* */

    /**
     * Load the properties in the reader
     * @param context - The context to use if needed
     */
    override fun loadProperties(context: Context?) {
        val assetManager = context!!.assets
        val inputStream = assetManager.open(PROPERTIES_FILE)
        mProperties = Properties()
        mProperties?.load(inputStream)
    }

    /**
     * Reads in the loaded properties the property with this name
     * @param name - The name of the property
     * @return [String] - The value of the property
     */
    override fun readProperty(name: String): String? {
        if (mProperties == null) throw UnloadedPropertiesException("The properties are null, did you load them?")
        return mProperties?.getProperty(name)
    }


    /* ************* *
     * INNER CLASSES *
     * ************* */

    /**
     * Exception to throw if the properties to rea on are null
     */
    class UnloadedPropertiesException(message: String) : IllegalStateException(message)

}