package com.example.mymoxy.di

import com.example.mymoxy.presentarion.FirstFragment
import com.example.mymoxy.tools.FirstFragmentScope
import dagger.Component

@FirstFragmentScope
@Component(
    dependencies = [MainActvitityComponent::class],
)
interface FirstFragmentComponent {
    fun inject(fragment: FirstFragment)
    @Component.Factory
    interface Factory {
        fun create(applicationComponent: MainActvitityComponent): FirstFragmentComponent
    }
}