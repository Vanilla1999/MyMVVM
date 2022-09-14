package com.example.mymvvm.di

import com.example.mymvvm.presentarion.FirstFragment
import com.example.mymvvm.tools.FirstFragmentScope
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