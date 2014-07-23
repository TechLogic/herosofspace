using UnityEngine;
using System.Collections;
using Life;


public class BoltMoving : MonoBehaviour
{

    public int speed = 10;
    public Vector3 direction;
    public int damage = 5;

    void Start()
    {
        
        direction.Normalize();
        direction.Scale(new Vector3(speed, speed, speed));

        rigidbody.AddForce(direction, ForceMode.Acceleration);
    }

    
    void OnCollisionEnter(Collision collision)
    {
       Collider collider =  collision.collider;


       Destroy(gameObject);
       LifeBehaviour life = collider.GetComponent<LifeBehaviour>();
       if (life != null)
       {
           life.TakeDamage(damage);
       }

    }
}
