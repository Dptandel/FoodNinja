package com.app.foodninja

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.app.foodninja.adapters.OnBoardingItemListAdapter
import com.app.foodninja.databinding.ActivityOnBoardingBinding
import com.app.foodninja.models.OnBoardingItem

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private var onboardingItemList = mutableListOf<OnBoardingItem>()
    private lateinit var onBoardingItemAdapter: OnBoardingItemListAdapter
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add items to the onboarding list
        onboardingItemList.apply {
            add(
                OnBoardingItem(
                    R.drawable.onboarding1,
                    "Find Your Comfort \nFood Here",
                    "Here you can find a Chef or Dish for every Taste and Color. Enjoy!!!"
                )
            )
            add(
                OnBoardingItem(
                    R.drawable.onboarding2,
                    "Food Ninja is Where Your \nComfort Food Lives",
                    "Enjoy a fast and smooth food delivery at your doorstep!!!"
                )
            )
        }

        onBoardingItemAdapter = OnBoardingItemListAdapter(this, onboardingItemList)
        binding.viewPager.adapter = onBoardingItemAdapter
        binding.viewPager.currentItem = currentIndex

        binding.btnNext.setOnClickListener {
            if (currentIndex < onboardingItemList.size - 1) {
                currentIndex++
                binding.viewPager.currentItem = currentIndex
            } else {
                // User has finished onboarding, mark it complete and redirect to LoginActivity
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }

        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                currentIndex = position

                // Change Next button text based on current page
                binding.btnNext.text =
                    if (currentIndex < onBoardingItemAdapter.count - 1) "Next" else "Finish"
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}