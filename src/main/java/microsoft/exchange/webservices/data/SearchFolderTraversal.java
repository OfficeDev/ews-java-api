package microsoft.exchange.webservices.data;

/**
 * Defines the scope of a search folder.
 */
public enum SearchFolderTraversal {

  // Items belonging to the root folder are retrieved.
  /**
   * The Shallow.
   */
  Shallow,

  // Items belonging to the root folder and its sub-folders are retrieved.
  /**
   * The Deep.
   */
  Deep

}
