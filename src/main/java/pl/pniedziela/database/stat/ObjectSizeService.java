package pl.pniedziela.database.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectSizeService {

	@Autowired
	ObjectSizeDao objectSizeDao;

	public List<ObjectSize> getAllObjectsSizes() {

		return objectSizeDao.getAllObjectSizes();
	}
}
