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


package pylapp.tapster.client.android.tools

import android.content.Context
import pylapp.tapster.client.android.assistants.AssistantStub
import pylapp.tapster.client.android.assistants.UiNotifierStub
import pylapp.tapster.client.android.assistants.snips.SnipsAssistantSkeleton
import pylapp.tapster.client.android.networks.HttpClientStub
import pylapp.tapster.client.android.networks.OkHttpHttpClientSkeleton
import pylapp.tapster.client.android.tools.properties.AssetsPropertiesReaderSkeleton
import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub
import pylapp.tapster.client.android.ui.UiNotifierSkeleton
import pylapp.tapster.client.android.ui.permissions.DexterPermissionsManagerSkeleton
import pylapp.tapster.client.android.ui.permissions.PermissionsManagerStub
import pylapp.tapster.client.android.ui.settings.LicensesDisplayerSkeleton
import pylapp.tapster.client.android.ui.settings.LicensesDisplayerStub
import pylapp.tapster.client.android.ui.taptargets.HelpersDisplayerStub
import pylapp.tapster.client.android.ui.taptargets.TapTargetHelpersDisplayerSkeleton
import pylapp.tapster.client.android.vox.AndroidTextToSpeechSkeleton
import pylapp.tapster.client.android.vox.TextToSpeechStub

/**
 * Class which provides methods to get feature objects.
 * These feature objects are the kinds of objects which may have several implementations according
 * to the needs of the app. These volatile implementations may be improved or replaced, so
 * this class provides objects through their stubs. Thus the other classes which use them won't be
 * impacted by the modifications of the implementations.
 *
 *
 * @author Pierre-Yves Lapersonne
 * @since 07/02/2018
 *
 * @version 1.0.0
 */
class FeaturesFactory {

    /**
     * Builds a [UiNotifierStub] to use to as to make notifications to the user interface.
     * Provides here a new instance of [UiNotifierSkeleton] which cna make toasts and update
     * recycler views from UI thread.
     *
     * @param context - The context to use, here must not be null with the [UiNotifierSkeleton]
     * @return [UiNotifierStub] -
     */
    fun buildUiNotifier(context: Context?): UiNotifierStub {
        return UiNotifierSkeleton(context!!)
    }

    /**
     * Builds an [AssistantStub] to use to as to have a chatbot assistant.
     * Provides here a new instance of [SnipsAssistantSkeleton] so as to have conversational
     * agent using Automatic Speech Recognition and Natural Language Understanding.
     *
     * @return [AssistantStub] -
     */
    fun buildAssistant(): AssistantStub {
        return SnipsAssistantSkeleton()
    }

    /**
     * Builds an [PropertiesReaderStub] to use to as to provide properties and in the end
     * configuration.
     * Provides here a new instance of [AssetsPropertiesReaderSkeleton] so as to load configuration
     * from properties file.
     *
     * @return [PropertiesReaderStub] -
     */
    fun buildPropertiesReader(): PropertiesReaderStub {
        return AssetsPropertiesReaderSkeleton()
    }

    /**
     * Builds an [LicensesDisplayerStub] to use to as to display the licenses of third party tools
     * in use.
     * Provides here a new instance of [LicensesDisplayerSkeleton].
     *
     * @return [LicensesDisplayerStub] -
     */
    fun buildLicensesDisplayer(): LicensesDisplayerStub {
        return LicensesDisplayerSkeleton()
    }

    /**
     * Builds an [HelpersDisplayerStub] to use to as to display the commandTap targets / pointers
     * to components so as to introduce them.
     * Provides here a new instance of [TapTargetHelpersDisplayerSkeleton].
     *
     * @return [HelpersDisplayerStub] -
     */
    fun buildTapTargetsDisplayer(): HelpersDisplayerStub {
        return TapTargetHelpersDisplayerSkeleton()
    }

    /**
     * Builds an [TextToSpeechStub] to use to as to vocalize texts.
     * Provides here a new instance of [AndroidTextToSpeechSkeleton].
     *
     * @return [TextToSpeechStub] -
     */
    fun buildTextToSpeechVocalizer(): TextToSpeechStub {
        return AndroidTextToSpeechSkeleton()
    }

    /**
     * Builds an [HttpClientStub] to use to as to send HTTP requests.
     * Provides here a new instance of [OkHttpHttpClientSkeleton].
     *
     * @return [HttpClientStub] -
     */
    fun buildHttpClient(): HttpClientStub {
        return OkHttpHttpClientSkeleton()
    }

    /**
     * Builds an [PermissionsManagerStub] to use to ask for permissions grants.
     * Provides here a new instance of [DexterPermissionsManagerSkeleton].
     *
     * @return [PermissionsManagerStub] -
     */
    fun buildPermissionsManager(): PermissionsManagerStub {
        return DexterPermissionsManagerSkeleton()
    }

}