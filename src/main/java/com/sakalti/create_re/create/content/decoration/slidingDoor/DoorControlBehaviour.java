package com.sakalti.create_re.content.decoration.slidingDoor;

import com.sakalti.create_re.foundation.blockEntity.SmartBlockEntity;
import com.sakalti.create_re.foundation.blockEntity.behaviour.BehaviourType;
import com.sakalti.create_re.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.sakalti.create_re.foundation.utility.NBTHelper;

import net.minecraft.nbt.CompoundTag;

public class DoorControlBehaviour extends BlockEntityBehaviour {
	
	public static final BehaviourType<DoorControlBehaviour> TYPE = new BehaviourType<>();
	
	public DoorControl mode;
	
	public DoorControlBehaviour(SmartBlockEntity be) {
		super(be);
		mode = DoorControl.ALL;
	}
	
	public void set(DoorControl mode) {
		if (this.mode == mode)
			return;
		this.mode = mode;
		blockEntity.notifyUpdate();
	}

	@Override
	public void write(CompoundTag nbt, boolean clientPacket) {
		NBTHelper.writeEnum(nbt, "DoorControl", mode);
		super.write(nbt, clientPacket);
	}
	
	@Override
	public void read(CompoundTag nbt, boolean clientPacket) {
		mode = NBTHelper.readEnum(nbt, "DoorControl", DoorControl.class);
		super.read(nbt, clientPacket);
	}
	
	@Override
	public BehaviourType<?> getType() {
		return TYPE;
	}

}
