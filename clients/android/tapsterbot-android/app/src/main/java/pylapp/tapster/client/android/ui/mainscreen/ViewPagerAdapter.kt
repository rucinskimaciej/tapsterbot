/*
    MIT License
    Copyright (c) 2018  Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)
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

package pylapp.tapster.client.android.ui.mainscreen

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Adapter to use for the fragments in the main screen.
 *
 * @constructor Creates a FragmentPagerAdapter
 * @author pylapp
 * @since 05/02/2018
 *
 * @version 1.0.0
 */
class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The list of fragments
     */
    private var mFragmentList: ArrayList<Fragment>? = null
    /**
     * The list of fragments" titles
     */
    private var mFragmentTitleList: ArrayList<String>? = null

    init {
        mFragmentList = ArrayList()
        mFragmentTitleList = ArrayList()
    }


    /* ********************************* *
     * METHODS FROM FragmentPagerAdapter *
     * ********************************* */

    /**
     * Returns the [Fragment] at this position
     *
     * @param position -
     * @return Fragment -
     */
    override fun getItem(position: Int): Fragment {
        return mFragmentList!![position]
    }

    /**
     * Returns the number of elements
     *
     * @return Int -
     */
    override fun getCount(): Int {
        return mFragmentList!!.size
    }

    /**
     * Returns the title of the page at this position
     *
     * @param position -
     * @return [CharSequence] -
     */
    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList!![position]
    }

    /**
     * Returns the object, a [Fragment] instance, at this position
     *
     * @param object -
     * @return Int -
     */
    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)

    }


    /* ************* *
     * OTHER METHODS *
     * ************* */

    /**
     * Adds a new fragment with the dedicated title in in the inner lists
     * so as to display it after
     */
    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList!!.add(fragment)
        mFragmentTitleList!!.add(title)
    }

}