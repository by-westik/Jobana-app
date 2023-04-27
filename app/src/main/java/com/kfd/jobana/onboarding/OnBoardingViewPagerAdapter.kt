package com.kfd.jobana.onboarding

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kfd.jobana.R

class OnBoardingViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle){

    private val fragmentList: ArrayList<Fragment> = createFragmentList()

    override fun createFragment(position: Int): Fragment {
        Log.d("adapter", "create Fragment")
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    private fun createFragmentList(): ArrayList<Fragment> {
        val list = ArrayList<Fragment>  ()
        list.add(OnBoardingFragment.newInstance("Создавайте объявления", "Создавайте, размещайте и редактируйте объявления не выходя из приложения", R.drawable.on_boarding_image_1))
        list.add(OnBoardingFragment.newInstance("Общайтесь в чате", "Обсуждайте детали заказа и исполнения с закачиками и специалистами", R.drawable.on_boarding_image_2))
        list.add(OnBoardingFragment.newInstance("Единый профиль", "Попробуйте себя в обоих ролях без дополнительной регистрации", R.drawable.on_boarding_image_3))

        return list
    }
}