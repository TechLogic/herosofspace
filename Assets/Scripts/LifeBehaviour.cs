using UnityEngine;
using System.Collections;
using System.Timers;
using System;

public class LifeBehaviour : MonoBehaviour {

    public int Health;
    public int Shield;
    public int MaxShield;
    public int MaxHealth;
    public TimeSpan ShieldRegenerationTime = new TimeSpan(0,0,10);
    public int ShieldRegenerationAmount;


    private DateTime HitTime  =System.DateTime.Now;
    private Timer RegenerationTimer;

    public bool Running;

    public LifeBehaviour(){
           RegenerationTimer = new Timer(1000);
           RegenerationTimer.Elapsed += new ElapsedEventHandler(_timer_Elapsed);
    }


    public void TakeDamage(int Damage)
    {
        int healthDamage = Damage - Shield;
        Shield -=  Damage;
        Health -= healthDamage;
        HitTime = System.DateTime.Now;
        RegenerationTimer.Stop();
        
    }

    private void RegenerateShield()
    {
        Shield += ShieldRegenerationAmount;
        if (Shield > MaxShield)
        {
            Shield = MaxShield;
        }
    }


    public void FixedUpdate()
    {
        Running = RegenerationTimer.Enabled;
           
        if (Shield < MaxShield)
        {
           
            TimeSpan s = new  TimeSpan(System.DateTime.Now.Ticks - HitTime.Ticks);
      
            TimeSpan oneSec = new TimeSpan(0,0,1);
            if ( ShieldRegenerationTime.Subtract(s) <= oneSec )
            {
             RegenerationTimer.Start();
            
            }
        }

    }


    private void _timer_Elapsed(object sender, ElapsedEventArgs e)
    {
	    RegenerateShield();
        if (Shield == MaxShield)
        {
            RegenerationTimer.Stop();
        }
    }

    }


