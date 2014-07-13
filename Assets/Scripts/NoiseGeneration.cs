using UnityEngine;
using System.Collections;
using System.Collections.Generic;
namespace NoiseGeneration
{

    public class NoiseGenerator
    {

        private int Width;
        private int Height;
        private float InitialSize;



        public Texture2D GenerateTexture(int Width, int Height, float Size)
        {
            this.Width = Width;
            this.Height = Height;
            InitialSize = Size;
            float[] noise = new float[Width * Height];
            Color[] pix = new Color[Width * Height];


            Texture2D texture = new Texture2D(Width, Height, TextureFormat.ARGB32, false);
            List<float[]> list = new List<float[]>();
            while (Size > 10)
            {
                list.Add(generateNoise(Size));
                Size /= 2;
            }

            Color front = new Color(Random.value * 2, Random.value * 2, Random.value * 2, 255f);
            //Color front = new Color(Color.red.r, Color.red.g, Color.red.b, 100f);
            Color back = new Color(Color.black.r, Color.black.g, Color.black.b, 255f);
            for (int i = 0; i < noise.Length; i++)
            {
                float NoiseValue= 0f;
                foreach (float[] f in list)
                {
                    NoiseValue += f[i];
                }
                NoiseValue /= list.Count;
          

                pix[i] = new Color(NoiseValue/4 * front.r + (1 - NoiseValue)*2 * back.r, NoiseValue/4 * front.g + (1 - NoiseValue)*2 * back.g, NoiseValue/4 * front.b + (1 - NoiseValue)*2 * back.b,NoiseValue*front.a+(1-NoiseValue)*back.a);
            }




            texture.SetPixels(pix);
            texture.Apply();


            return texture;
        }





        private float[] generateNoise(float Size)
        {

            float[] noise = new float[Width * Height];
            int xOrg = Random.Range(-10000, 10000);
            int yOrg = Random.Range(-10000, 10000);

            float y = 0.0F;
            while (y < Height)
            {
                float x = 0.0F;
                while (x < Width)
                {
                    float xCoord = xOrg + x / Width * Size;
                    float yCoord = yOrg + y / Height * Size;
                    noise[Mathf.RoundToInt(y * Width + x)] =  Mathf.PerlinNoise(xCoord, yCoord) /2;
                    x++;
                }
                y++;
            }
            return noise;




        }
    }
}
 