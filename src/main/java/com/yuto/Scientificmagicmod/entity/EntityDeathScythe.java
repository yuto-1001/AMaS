package com.yuto.Scientificmagicmod.Entity;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

public class EntityDeathScythe extends Entity implements IProjectile {
	/* 地中判定に使うもの */
	protected int xTile = -1;
	protected int yTile = -1;
	protected int zTile = -1;
	protected Block inTile;
	protected int inData;
	protected boolean inGround;
	/* この弾を撃ったエンティティ */
	public Entity shootingEntity;

	/* 地中・空中にいる時間 */
	protected int ticksInGround;
	protected int ticksInAir;
	protected int livingTimeCount = 0;

	/* ダメージの大きさ */
	protected final double damage;

	/* 前進速度 */
	protected double speed = 1.0D;

	/* 幅 */
	protected final double range;

	/* ノックバックの大きさ */
	protected final int coolTime;

	protected boolean isCorrosion = false;
	protected boolean isFire = false;

	protected float f6 = 10.0F;

	public EntityDeathScythe(World par1World) {
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.damage = 21.0D;
		this.range = 1.0D;
		this.coolTime = 10;
	}

	public EntityDeathScythe(World par1World, float size, double thisdamage, double thisrange, int thisCoolTime) {
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.setSize(size, size);
		this.range = thisrange;
		this.damage = thisdamage;
		this.coolTime = thisCoolTime;
	}

	public EntityDeathScythe(World par1World, EntityLivingBase par2EntityLivingBase, float speed, float speed2,
			double damage, double range, int cool) {
		this(par1World, 0.5F, damage, range, cool);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLivingBase;
		this.yOffset = 0.0F;

		// 初期状態での向きの決定
		this.setLocationAndAngles(par2EntityLivingBase.posX,
				par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight() - 0.6D, par2EntityLivingBase.posZ,
				par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);

		this.setPosition(this.posX, this.posY, this.posZ);

		float f1 = worldObj.rand.nextFloat() * 0.1F - 0.05F;
		float f2 = worldObj.rand.nextFloat() * 0.1F - 0.05F;
		float f3 = worldObj.rand.nextFloat() * 0.1F - 0.05F;

		// 初速度
		this.motionX = f1
				+ ((double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper
						.cos(this.rotationPitch / 180.0F * (float) Math.PI)));
		this.motionZ = f2
				+ ((double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper
						.cos(this.rotationPitch / 180.0F * (float) Math.PI)));
		this.motionY = f3 + ((double) (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI)));
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed, speed2);
	}

	@Override
	protected void entityInit() {

	}

	@Override
	public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= f2;
		par3 /= f2;
		par5 /= f2;
		par1 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
		par3 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
		par5 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
		par1 *= par7;
		par3 *= par7;
		par5 *= par7;
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;
		float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
		this.setPosition(par1, par3, par5);
		this.setRotation(par7, par8);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double par1, double par3, double par5) {
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	/*
	 * Tick毎に呼ばれる更新処理。
	 * 速度の更新、衝突判定などをここで行う。
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();

		livingTimeCount++;
		if (livingTimeCount > 300){
			this.setDead();
		}
		++this.ticksInAir;
		// ブロックとの衝突判定
		Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ
				+ this.motionZ);
		MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec3, vec31, false, true, false);
		vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
		vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ
				+ this.motionZ);
			// ブロックに当たった
		if (movingobjectposition != null) {
			vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord,
					movingobjectposition.hitVec.zCoord);
		}
			// Entityとの衝突判定。
		// コンストラクタで入れたrangeの分だけ当たり判定を拡大し、接触しているEntityのリストを得ます。
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
				this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(range, range, range));
		double d0 = 0.0D;
		int l;
		float f1;
		boolean isVillager = false;
		ArrayList<MovingObjectPosition> entityList = new ArrayList<MovingObjectPosition>();
			// 1ブロック分の範囲内にいるエンティティ全てに対して繰り返す
		// ここは、EntityArrowでは一体だけに対象を絞っているが、このMODでは全てを対象に取る
		for (l = 0; l < list.size(); ++l) {
			Entity entity1 = (Entity) list.get(l);
			Entity entity = null;
				// 自傷防止のため、発射物自身or発射後5tick以外だとすりぬける
			if (entity1.canBeCollidedWith() && entity1 != this.shootingEntity) {
				f1 = 0.5F;
				// 上下方向は広めに見る
				AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f1, 1.0F, f1);
				MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);
					if (movingobjectposition1 != null) {
					double d1 = vec3.distanceTo(movingobjectposition1.hitVec);
						if (d1 < d0 || d0 == 0.0D) {
						// arrowと異なり、あたったEntityすべてをリストに入れる
						entityList.add(new MovingObjectPosition(entity1));
						d0 = d1;
					}
				}
			}
		}
			/*
		 * 当たったエンティティそれそれについての判定部分。
		 * ここで特定の種類のエンティティに当たらないようにできる。
		 */
		if (entityList != null && !entityList.isEmpty()) {
			// リストを回す
			for (int n = 0; n < entityList.size(); n++) {
				Entity target = entityList.get(n).entityHit;
					if (target instanceof EntityPlayer) {
					// プレイヤーに当たった時
					EntityPlayer entityplayer = (EntityPlayer) target;
						if (!entityplayer.capabilities.disableDamage || !(this.shootingEntity instanceof EntityPlayer)) {
						entityList.remove(n);
					}
				} else if (target == this.shootingEntity) {
					// 対象が撃った本人の場合も当たらない
					entityList.remove(n);
				}
			}
		}
			float f3;
			// 当たったあとの処理
		// まずはリストから
		if (entityList != null && !entityList.isEmpty()) {
			for (MovingObjectPosition target : entityList) {
				if (target != null && target.entityHit != null) {
					// ダメージは固定
					int i1 = MathHelper.ceiling_double_int(this.damage);
					// 0~2程度の乱数値を上乗せ
					i1 += this.rand.nextInt(3);
					// 別メソッドでダメージソースを確認 …ここでは、仮としてLavaに固定にしている
					DamageSource damagesource = this.thisDamageSource(this.shootingEntity);
						if (target.entityHit instanceof IProjectile && !(target.entityHit instanceof EntityDeathScythe)) {
						// 対象が矢などの飛翔Entityの場合、打ち消すことが出来る
						target.entityHit.setDead();
					} else {
							// ダメージを与える
						if (target.entityHit.attackEntityFrom(damagesource, i1)) {
							// ダメージを与えることに成功したら以下の処理を行う
							if (target.entityHit instanceof EntityLivingBase) {
								EntityLivingBase living = (EntityLivingBase) target.entityHit;
									// 腐食による弱体化potion付与
								if (this.isCorrosion) {
									living.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 200, 0));
								}
									// 着火
								if (this.isFire) {
									// 着火はせず、アマ貫の炎属性ダメージが入る
									living.attackEntityFrom(DamageSource.onFire, 1.0F);
								}
									// 無敵時間
								if (this.coolTime > 0) {
									target.entityHit.hurtResistantTime = coolTime;
								} else {
									target.entityHit.hurtResistantTime = 0;
								}
									// マルチプレイ時に、両者がプレイヤーだった時のパケット送信処理
								if (this.shootingEntity != null && target.entityHit != this.shootingEntity
										&& target.entityHit instanceof EntityPlayer
										&& this.shootingEntity instanceof EntityPlayerMP) {
									((EntityPlayerMP) this.shootingEntity).playerNetServerHandler
											.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
								}
							}
						}
					}
				}
			}
		}
		if (movingobjectposition != null)// blockの接触判定をとっておいたのをここで処理
		{
			// 当たったブロックを記憶し、次Tickで上の方の埋まり判定に使う
			this.xTile = movingobjectposition.blockX;
			this.yTile = movingobjectposition.blockY;
			this.zTile = movingobjectposition.blockZ;
			this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
			this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
				if (this.inTile != null) {
				this.inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
					// 腐食属性の時 configでONならブロック破壊効果を起こす
				if (this.isCorrosion) {
					if (inTile == Blocks.stone) {
						this.worldObj.setBlock(xTile, yTile, zTile, Blocks.cobblestone);
					} else if (inTile == Blocks.grass) {
						this.worldObj.setBlock(xTile, yTile, zTile, Blocks.dirt);
					} else if (inTile == Blocks.cobblestone || inTile == Blocks.dirt || inTile == Blocks.gravel
							|| inTile == Blocks.sand) {
						this.worldObj.setBlockToAir(xTile, yTile, zTile);
					}
				}
					// フレイムエンチャント時にWood属性のブロックを破壊
				if (this.isFire) {
					if (inTile instanceof BlockLeavesBase || inTile instanceof BlockBush) {
						this.worldObj.setBlockToAir(xTile, yTile, zTile);
					} else if (inTile.getMaterial() == Material.wood) {
						this.worldObj.setBlockToAir(xTile, yTile, zTile);
						boolean b = false;
						ArrayList<ItemStack> ores = OreDictionary.getOres("logWood");
						for (ItemStack log : ores) {
							if (log == null || log.getItem() == null)
								continue;
							if (log.getItem() == Item.getItemFromBlock(inTile)) {
								b = true;
								break;
							}
						}
						// 原木を破壊した時に木炭をドロップさせている
						if (b && !worldObj.isRemote) {
							ItemStack c = new ItemStack(Items.coal, 1, 1);
							EntityItem drop = new EntityItem(worldObj, xTile, yTile, zTile, c);
							worldObj.spawnEntityInWorld(drop);
						}
					} else if (inTile.getMaterial() == Material.snow || inTile.getMaterial() == Material.ice) {
						this.worldObj.setBlockToAir(xTile, yTile, zTile);
					}
					if (worldObj.isAirBlock(xTile, yTile + 1, zTile)
							&& inTile.isFlammable(worldObj, xTile, yTile, zTile, ForgeDirection.UP)) {
						worldObj.setBlock(xTile, yTile + 1, zTile, Blocks.fire);
					}
				}
			}
		} else { // どうもmovingobjectpositionで取りこぼすようなので、nullの場合も同じ事をしている。ひょっとしてすごく無駄なことしているのでは…
			// inTile(埋まり判定)は更新しない。
			int tarX = MathHelper.floor_double(this.posX);
			int tarY = MathHelper.floor_double(this.posY);
			int tarZ = MathHelper.floor_double(this.posZ);
			Block target = worldObj.getBlock(tarX, tarY, tarZ);
			if (target != null && target.getMaterial() != Material.air) {
				// 上の処理より出来ることを少なめにしていたりする
				if (this.isCorrosion) {
					if (inTile == Blocks.stone) {
						this.worldObj.setBlock(xTile, yTile, zTile, Blocks.cobblestone);
					} else if (inTile == Blocks.grass) {
						this.worldObj.setBlock(xTile, yTile, zTile, Blocks.dirt);
					}
				}
				if (this.isFire) {
					if (inTile instanceof BlockLeavesBase || inTile instanceof BlockBush) {
						this.worldObj.setBlockToAir(xTile, yTile, zTile);
					} else if (target.getMaterial() == Material.snow || target.getMaterial() == Material.ice) {
						this.worldObj.setBlockToAir(tarX, tarY, tarZ);
					}
					// あらゆるブロックに着火
					if (worldObj.isAirBlock(tarX, tarY + 1, tarZ)
					/* && target.isFlammable(worldObj, tarX, tarY, tarZ, ForgeDirection.UP) */) {
						worldObj.setBlock(tarX, tarY + 1, tarZ, Blocks.fire);
					}
				}
			}
		}

		// 改めてポジションに速度を加算。向きも更新。
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
		this.rotationPitch = (float) (Math.atan2(this.motionY, f2) * 180.0D / Math.PI);

		while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
			this.prevRotationPitch -= 360.0F;
		}

		while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

		// 徐々に減速する
		float f4 = 0.9F;
		float f5 = 0.25F;
		// 水中に有る
		if (this.isInWater()) {
			if (this.isBurning()) {
				this.extinguish();
			}

			for (int j1 = 0; j1 < 4; ++j1) {
			this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f5, this.posY -
			this.motionY * f5,
			this.posZ - this.motionZ * f5, this.motionX, this.motionY, this.motionZ);
			}
		}
		this.worldObj.spawnParticle("witchMagic", this.posX - this.motionX * f5, this.posY -
		this.motionY * f5,
		this.posZ - this.motionZ * f5, this.motionX, this.motionY, this.motionZ);

		this.motionX *= f4;
		this.motionY *= f4;
		this.motionZ *= f4;


		// 一定以上遅くなったら消える
		if (this.worldObj.isRemote && (this.motionX * this.motionX + this.motionZ * this.motionZ) < 0.001D) {
			this.setDead();
		}
		this.setPosition(this.posX, this.posY, this.posZ);
		this.func_145775_I();
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}

	@Override
	public boolean canAttackWithItem() {
		return false;
	}

	/* 落下速度 */
	public float fallSpeed() {
		return 0.0F;
	}

	public void setCorrosion(boolean b) {
		this.isCorrosion = b;
	}

	public boolean getCorrosion() {
		return this.isCorrosion;
	}

	public void setAdvFire(boolean b) {
		this.isFire = b;
	}

	public boolean getAdvFire() {
		return this.isFire;
	}

	/* ダメージソースのタイプ */
	public DamageSource thisDamageSource(Entity entity) {
		return DamageSource.wither;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
	}

}