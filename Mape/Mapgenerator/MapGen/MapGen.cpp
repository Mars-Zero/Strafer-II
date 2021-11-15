#include <fstream>
#include <iostream>
#include <Windows.h>
#include <commdlg.h>
#include <sstream>
#include <cstddef> 
#include <tileson.h>

namespace fisier {
	std::string fisierFolosit;
	bool fisierIncarcat = false;
    std::ofstream fout("matrix.out");
}
namespace maptrix {
    short int mapMatrix[10001][10001];
    int mWidth = 0, mHeight = 0;
}

void openFile()
{

	OPENFILENAMEA ofn;
	CHAR szFile[260] = { 0 };
	CHAR currentDir[256] = { 0 };
	ZeroMemory(&ofn, sizeof(OPENFILENAME));
	ofn.lStructSize = sizeof(OPENFILENAME);
	ofn.lpstrFile = szFile;
	ofn.nMaxFile = sizeof(szFile);

	if (GetCurrentDirectoryA(256, currentDir)) {
		ofn.lpstrInitialDir = currentDir;
	}

	ofn.nFilterIndex = 1;
	ofn.Flags = OFN_PATHMUSTEXIST | OFN_FILEMUSTEXIST | OFN_NOCHANGEDIR;



	if (GetOpenFileNameA(&ofn) == TRUE) {
		fisier::fisierFolosit = ofn.lpstrFile;
		fisier::fisierIncarcat = true;
		return;

	}

	fisier::fisierIncarcat = false;

   
}


void matrixGen(short int mapMatrix[10001][10001], int width, int height, std::ofstream& fout) {
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            fout << mapMatrix[i][j] << " ";
        }
        fout << "\n";
    }
}


int main() {
	openFile();


	tson::Tileson t;
	std::unique_ptr<tson::Map> map = t.parse(fs::path(fisier::fisierFolosit));
    
    

    if (map->getStatus() == tson::ParseStatus::OK)
    {
		
       
        tson::Layer* layer = map->getLayer("Tile Layer 2");

        if (layer->getType() == tson::LayerType::TileLayer)
        {
            maptrix::mWidth = map->getSize().x;
            maptrix::mHeight = map->getSize().y;
            //pos = position in tile units
            for (auto& [pos, tileObject] : layer->getTileObjects()) //Loops through absolutely all existing tileObjects
            {
                tson::Tileset* tileset = tileObject.getTile()->getTileset();
                std::cout << tileset->getName()<<" ";

                tson::Vector2f position = tileObject.getPosition();
                int pX = position.x;
                int pY = position.y;
                std::cout << position.x << " " << position.y << '\n';

                if (tileset->getName() == "apa1") {
                    maptrix::mapMatrix[pY / 64][pX / 64] = -1;
                }


                //Here you can determine the offset that should be set on a sprite
                //Example on how it would be done using SFML (where sprite presumably is a member of a generated game object):
                //sf::Sprite *sprite = storeAndLoadImage(tileset->getImage().u8string(), {0, 0});
                //if (sprite != nullptr)
                //{
                //    sprite->setTextureRect({drawingRect.x, drawingRect.y, drawingRect.width, drawingRect.height});
                //    sprite->setPosition({position.x, position.y});
                //    m_window.draw(*sprite);
                //}
            }
        }

    } 

    matrixGen(maptrix::mapMatrix, maptrix::mWidth, maptrix::mHeight, fisier::fout);


        return 0;
}