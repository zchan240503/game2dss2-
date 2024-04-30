package main;

import entity.Entity;

public class solidCollectionCheck {
	GamePannel gp;
	public solidCollectionCheck(GamePannel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.soidArena.x;
		int entityRightWorldX = entity.worldX + entity.soidArena.x +entity.soidArena.width;
		int entityTopWorldY = entity.worldY + entity.soidArena.y;
		int entityBottomWorldY = entity.worldY + entity.soidArena.y +entity.soidArena.height;
		
		
		int entityleftCol = entityLeftWorldX / gp.TitleSize;
		int entityrightCol = entityRightWorldX / gp.TitleSize;
		int entitytopRow = entityTopWorldY / gp.TitleSize;
		int entitybottomRow = entityBottomWorldY / gp.TitleSize;
		
		int TileNum1, TileNum2;
		
		switch(entity.derection) {
		case "up":
			entitytopRow = (entityTopWorldY - entity.speed) / gp.TitleSize;
			TileNum1 = gp.tileM.mapTileNum[entityleftCol][entitytopRow];
			TileNum2 = gp.tileM.mapTileNum[entityrightCol][entitytopRow];
			if(gp.tileM.tile[TileNum1].collision == true || gp.tileM.tile[TileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entitybottomRow = (entityBottomWorldY + entity.speed) / gp.TitleSize;
			TileNum1 = gp.tileM.mapTileNum[entityleftCol][entitybottomRow];
			TileNum2 = gp.tileM.mapTileNum[entityrightCol][entitybottomRow];
			if(gp.tileM.tile[TileNum1].collision == true || gp.tileM.tile[TileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityleftCol = (entityLeftWorldX - entity.speed) / gp.TitleSize;
			TileNum1 = gp.tileM.mapTileNum[entityleftCol][entitytopRow];
			TileNum2 = gp.tileM.mapTileNum[entityleftCol][entitybottomRow];
			if(gp.tileM.tile[TileNum1].collision == true || gp.tileM.tile[TileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityrightCol = (entityRightWorldX + entity.speed) / gp.TitleSize;
			TileNum1 = gp.tileM.mapTileNum[entityrightCol][entitytopRow];
			TileNum2 = gp.tileM.mapTileNum[entityrightCol][entitybottomRow];
			if(gp.tileM.tile[TileNum1].collision == true || gp.tileM.tile[TileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	public int check_ob(Entity entity, boolean player) {
		int index = 999;
		for (int i = 0; i < gp.obj.length; i++) {
			//System.out.println(gp.obj.length);
			if(gp.obj[i] != null) {
				entity.soidArena.x = entity.worldX + entity.soidArena.x;
				entity.soidArena.y = entity.worldY + entity.soidArena.y;
				
				
				gp.obj[i].soidArena.x = gp.obj[i].worldX + gp.obj[i].soidArena.x;
				gp.obj[i].soidArena.y = gp.obj[i].worldY + gp.obj[i].soidArena.y;
				// System.out.println(entity.soidArena.x + " " + entity.soidArena.y + " " + gp.obj[i].soidArena.x + " " + gp.obj[i].soidArena.y);
				switch(entity.derection) {
				case"up":
					entity.soidArena.y -= entity.speed;
					if(entity.soidArena.intersects(gp.obj[i].soidArena)) {
						System.out.println("W va cham");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case"down":
					entity.soidArena.y += entity.speed; 
					if(entity.soidArena.intersects(gp.obj[i].soidArena)) {
						System.out.println("d va cham");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case"left":
					entity.soidArena.x -= entity.speed;
					if(entity.soidArena.intersects(gp.obj[i].soidArena)) {
						System.out.println("l va cham");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case"right":
					entity.soidArena.x += entity.speed;
					if(entity.soidArena.intersects(gp.obj[i].soidArena)) {
						System.out.println("r va cham");
						if(gp.obj[i].collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
						
					}break;
					
				}
				entity.soidArena.x = entity.soidArenaDefauldX;
				entity.soidArena.y = entity.soidArenaDefauldY;
				gp.obj[i].soidArena.x = gp.obj[i].soidArenaDefaultX; 
				gp.obj[i].soidArena.y = gp.obj[i].soidArenaDefaultY;
			}
			//if(gp.obj[i] == null) break;
		}
		//System.out.println(index);
		return index;
	}
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;
		for (int i = 0; i < target.length; i++) {
			//System.out.println(gp.obj.length);
			if(target[i] != null) {
				entity.soidArena.x = entity.worldX + entity.soidArena.x;
				entity.soidArena.y = entity.worldY + entity.soidArena.y;
				
				
				target[i].soidArena.x = target[i].worldX + target[i].soidArena.x;
				target[i].soidArena.y = target[i].worldY + target[i].soidArena.y;
				// System.out.println(entity.soidArena.x + " " + entity.soidArena.y + " " + gp.obj[i].soidArena.x + " " + gp.obj[i].soidArena.y);
				switch(entity.derection) {
				case"up":
					entity.soidArena.y -= entity.speed;
					if(entity.soidArena.intersects(target[i].soidArena)) {
						System.out.println("W va cham");
							entity.collisionOn = true;
							index = i;
						}
					break;
				case"down":
					entity.soidArena.y += entity.speed; 
					if(entity.soidArena.intersects(target[i].soidArena)) {
						System.out.println("d va cham");
							entity.collisionOn = true;
							index = i;
						}
					break;
				case"left":
					entity.soidArena.x -= entity.speed;
					if(entity.soidArena.intersects(target[i].soidArena)) {
						System.out.println("l va cham");
							entity.collisionOn = true;
							index = i;
						}
					break;
				case"right":
					entity.soidArena.x += entity.speed;
					if(entity.soidArena.intersects(target[i].soidArena)) {
						System.out.println("r va cham");
							entity.collisionOn = true;
							index = i;
						
					}break;
					
				}
				entity.soidArena.x = entity.soidArenaDefauldX;
				entity.soidArena.y = entity.soidArenaDefauldY;
				target[i].soidArena.x = target[i].soidArenaDefauldX; 
				target[i].soidArena.y = target[i].soidArenaDefauldY;
			}
			//if(gp.obj[i] == null) break;
		}
		//System.out.println(index);
		return index;
	}
	public void checkPlayer(Entity entity) {
		//int index = 999
			//System.out.println(gp.obj.length);
			if(gp.player != null) {
				entity.soidArena.x = entity.worldX + entity.soidArena.x;
				entity.soidArena.y = entity.worldY + entity.soidArena.y;
				
				
				gp.player.soidArena.x = gp.player.worldX + gp.player.soidArena.x;
				gp.player.soidArena.y = gp.player.worldY + gp.player.soidArena.y;
				// System.out.println(entity.soidArena.x + " " + entity.soidArena.y + " " + gp.obj[i].soidArena.x + " " + gp.obj[i].soidArena.y);
				switch(entity.derection) {
				case"up":
					entity.soidArena.y -= entity.speed;
					if(entity.soidArena.intersects(gp.player.soidArena)) {
						System.out.println("W va cham");
							entity.collisionOn = true;
							//index = i;
						}
					break;
				case"down":
					entity.soidArena.y += entity.speed; 
					if(entity.soidArena.intersects(gp.player.soidArena)) {
						System.out.println("d va cham");
							entity.collisionOn = true;
							//index = i;
						}
					break;
				case"left":
					entity.soidArena.x -= entity.speed;
					if(entity.soidArena.intersects(gp.player.soidArena)) {
						System.out.println("l va cham");
							entity.collisionOn = true;
							//index = i;
						}
					break;
				case"right":
					entity.soidArena.x += entity.speed;
					if(entity.soidArena.intersects(gp.player.soidArena)) {
						System.out.println("r va cham");
							entity.collisionOn = true;
							//index = i;
						
					}break;
					
				}
				entity.soidArena.x = entity.soidArenaDefauldX;
				entity.soidArena.y = entity.soidArenaDefauldY;
				gp.player.soidArena.x = gp.player.soidArenaDefauldX; 
				gp.player.soidArena.y = gp.player.soidArenaDefauldY;
			}
			//if(gp.obj[i] == null) break;
		}
		//System.out.println(index);
	}
