package com.tcl.user.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tcl.user.model.TvSetting;
import com.tcl.user.repository.TvSettingRepository;
import com.tcl.user.util.BasedErrorHandleController;
import com.tcl.user.util.CustomeHttpStatusCodeException;
import com.tcl.user.util.HeaderUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class TvSettingResource extends BasedErrorHandleController{
	private final Logger log = LoggerFactory.getLogger(TvSettingResource.class);

	@Inject
	private TvSettingRepository tvSettingRepository;

	/**
     * POST  /tvSetting -> Create a new tvSetting.
     */
	@ApiOperation(value = "create tvSetting api", httpMethod = "POST", response = TvSetting.class, notes = "create tvSetting")
    @RequestMapping(value = "/tvSetting",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TvSetting> createTvSetting(@Valid @RequestBody TvSetting tvsetting) throws URISyntaxException {
        log.debug("REST request to save TvSetting : {}", tvsetting);

        if (tvSettingRepository.findByUsername(tvsetting.getUsername()) != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("TvSetting", "usernameexists", "A new tvsetting cannot already have an ID")).body(null);
        }
        TvSetting result = tvSettingRepository.save(tvsetting);
        return ResponseEntity.created(new URI("/api/tvSetting/" + result.getUsername()))
            .headers(HeaderUtil.createEntityCreationAlert("tvSetting", result.getUsername().toString()))
            .body(result);
    }

    /**
     * PUT  /tvSetting -> Updates an existing tvSetting.
     */
	@ApiOperation(value = "update tvSetting api", httpMethod = "PUT", response = TvSetting.class, notes = "update tvSetting")
    @RequestMapping(value = "/tvSetting",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TvSetting> updateTvSetting(@Valid @RequestBody TvSetting tvsetting) throws URISyntaxException {
        log.debug("REST request to update ThirdServiceInfo : {}", tvsetting);
        if (tvSettingRepository.findByUsername(tvsetting.getUsername())== null) {
            return createTvSetting(tvsetting);
        }
        TvSetting result = tvSettingRepository.save(tvsetting);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("tvsetting", tvsetting.getUsername().toString()))
            .body(result);
    }

    /**
     * DELETE  /tvSetting/:id -> delete the "id" tvSetting.
     */
	@ApiOperation(value = "delete tvSetting api", httpMethod = "DELETE", response = TvSetting.class, notes = "delete tvSetting")
    @RequestMapping(value = "/tvSetting",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTvSetting(@RequestParam String userId) {
        log.debug("REST request to delete tvSetting : {}", userId);

        TvSetting tvsetting=tvSettingRepository.findByUsername(userId);
        if(tvsetting==null)
//        	return ResponseEntity.badRequest().headers(HeaderUtil.createAlert("tvFavorites not found", mediaId)).body(null);
        	throw new CustomeHttpStatusCodeException(400, "tvFavorites not found, delete failed");
        tvSettingRepository.delete(tvsetting);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("tvSetting", userId.toString())).build();
    }

    /**
     * GET  /tvSetting -> get all the tvSetting.
     */
	@ApiOperation(value = "get alltvSetting api", httpMethod = "GET", response = TvSetting.class, notes = "get alltvSetting")
    @RequestMapping(value = "/tvSetting/all",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<TvSetting> getAllTvSetting() {
        log.debug("REST request to get all TvSetting");
        return tvSettingRepository.findAll();
            }


    /**
     * GET  /tvSetting -> get tvSetting of specific user.
     */
    @ApiOperation(value = "get tvSetting by user api", httpMethod = "GET", response = TvSetting.class, notes = "get tvSetting by user")
    @RequestMapping(value = "/tvSetting",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> getTvSettingByUser(@RequestParam String userId) {
        log.debug("REST request to get TvSetting of specific user:"+userId);
        TvSetting s=null;

        s = tvSettingRepository.findByUsername(userId);
        if(null==s){
        	throw new CustomeHttpStatusCodeException(400, "tvFavorites not found, delete failed");
        }
//        	Map<String,String> result = new HashMap<String,String>();
//        	result.put("error", "Username not found");
//			return new ResponseEntity<>(result,HttpStatus.CONFLICT);

        return new ResponseEntity<>(s, HttpStatus.OK);
            }

//    /**
//     * GET  /tvSetting/:id -> get the "id" tvSetting.
//     */
//    @RequestMapping(value = "/tvSetting/{id}",
//        method = RequestMethod.GET,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
//    public ResponseEntity<TvSetting> getTvSettingById(@PathVariable Long id) {
//        log.debug("REST request to get TvSetting : {}", id);
//        TvSetting tvsetting = tvSettingRepository.findOne(id);
//        return Optional.ofNullable(tvsetting)
//            .map(result -> new ResponseEntity<>(
//                result,
//                HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
}
