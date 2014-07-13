using UnityEngine;
using System.Collections;
using NoiseGeneration;
public class LevelGeneration : MonoBehaviour {

	// Use this for initialization
	void Start () {

        //Instantiate Player
       GameObject player =  Instantiate(Resources.Load("Player"), new Vector3(0, 0, 0), new Quaternion()) as GameObject;
       player.transform.name = "Player";


      
      // CreateAsteroids();
       CreateAsteroids();
        


        //Generate Skybox
        NoiseGenerator noise = new NoiseGenerator();
        Texture2D texture = noise.GenerateTexture(1080, 720,10000f);
        texture.wrapMode = TextureWrapMode.Repeat;

        for (int i = 0; i <= Random.Range(2000, 3500); i++)
        {
            float f = Random.value;
            if (f >= 0.1)
            {
                texture.SetPixel(Random.Range(0, texture.width), Random.Range(0, texture.height), Color.white);
            }
            else
            {
               int x =  Random.Range(0, texture.width) ;
               int y = Random.Range(0, texture.height);



               texture.SetPixel(x, y, Color.white);
               texture.SetPixel(x + 1, y, Color.white);
               texture.SetPixel(x, y + 1, Color.white);
               texture.SetPixel(x + 1, y + 1, Color.white);
    
            }
            }
        texture.Apply();

        GameObject camera = Camera.main.gameObject;
        Skybox skybox = camera.GetComponent<Skybox>();
        if (skybox == null)
        {
            skybox = camera.AddComponent<Skybox>();
        }
        skybox.material = new Material(Shader.Find("RenderFX/Skybox"));
        skybox.material.SetTexture("_FrontTex", texture);
        skybox.material.SetTexture("_BackTex", texture);
        skybox.material.SetTexture("_LeftTex", texture);
        skybox.material.SetTexture("_RightTex", texture);
        skybox.material.SetTexture("_UpTex", texture);
        skybox.material.SetTexture("_DownTex", texture);
            
      

	}

    



    private void CreateAsteroids()
    {
        //Create Astreoids

        var astroid = Resources.Load("Asteroid");


        GameObject ParentAsteroid = new GameObject("Asteroids");

        //Random.Range(1000, 2500)
        for (int i = 0; i <= 2500; i++)
        {
            GameObject createdAsteroid = Instantiate(astroid, new Vector3(Random.Range(-1000, 1000), Random.Range(-1000, 1000), Random.Range(-1000, 1000)), Random.rotation) as GameObject;
            createdAsteroid.name = "Asteroid" + i.ToString();
            int size = Random.Range(1, 20);
            createdAsteroid.transform.parent = ParentAsteroid.transform;
            createdAsteroid.transform.localScale = new Vector3(size, size, size);
            Vector3 force = new Vector3(Random.Range(-10, 10), Random.Range(-10, 10), Random.Range(-10, 10));

            createdAsteroid.rigidbody.AddForce(force, ForceMode.Acceleration);


        }
    }



    
	
}
