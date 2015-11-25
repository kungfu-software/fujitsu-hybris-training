package au.com.fujitsu.trainingfacade.jalo;

import au.com.fujitsu.trainingfacade.constants.TrainingfacadeConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class TrainingfacadeManager extends GeneratedTrainingfacadeManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( TrainingfacadeManager.class.getName() );
	
	public static final TrainingfacadeManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (TrainingfacadeManager) em.getExtension(TrainingfacadeConstants.EXTENSIONNAME);
	}
	
}
